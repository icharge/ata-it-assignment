import { useState, useCallback, useEffect } from "react";
import { Instrument, InstrumentTypeKey } from "../../types/Instrument";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { formatDateTime, formatDateToDDMMYYYY } from "../../utils/date";

const InstrumentSearchCriteriaSchema = z.object({
  instrumentType: z.enum(["MUTUAL_FUND", "FIXED_INCOME"]).optional(),
  name: z.string().optional(),
  accountNumber: z.string().optional(),
  months: z.number().int().positive().optional(),
  interestRate: z.number().gte(0).nullable(),
  status: z.string().optional(),
});

type InstrumentSearchCriteria = z.infer<typeof InstrumentSearchCriteriaSchema>;

interface InstrumentSearchProps {
  instrumentType: InstrumentTypeKey;
}

const FundSearch: React.FC<InstrumentSearchProps> = ({ instrumentType }) => {
  const activeTab = instrumentType;

  const {
    getValues,
    register,
    handleSubmit,
    reset,
    formState: { isSubmitting, errors },
  } = useForm<InstrumentSearchCriteria>({
    resolver: zodResolver(InstrumentSearchCriteriaSchema),
    defaultValues: {
      instrumentType,
      name: "",
      accountNumber: "",
      months: 3,
      interestRate: null,
      status: "ACTIVE",
    },
  });

  if (errors) {
    console.log(errors);
  }

  const [instruments, setInstruments] = useState<Instrument[]>([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);

  // Build query params from criteria & activeTab
  const buildQueryParams = useCallback(
    (data: InstrumentSearchCriteria, page: number = 0) => {
      const params = new URLSearchParams();
      params.append("instrumentType", data.instrumentType as string);
      params.append("page", page.toString());
      params.append("size", "10");

      if (data.name) {
        params.append("name", data.name);
      }

      if (data.accountNumber) {
        params.append("accountNumber", data.accountNumber);
      }

      if (data.months) {
        params.append("months", data.months.toString());
      }

      if (data.interestRate) {
        params.append("interestRate", data.interestRate.toString());
      }

      return params.toString();
    },
    []
  );

  const fetchInstruments = useCallback(async (query?: string) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/instruments/search?${query}`
      );
      if (response.ok) {
        const data = await response.json();
        setInstruments(data.content);
        setTotalPages(data.totalPages);
        setTotalElements(data.totalElements);
      } else {
        console.error("Error fetching instruments:", response.statusText);
      }
    } catch (error) {
      console.error("Error fetching instruments:", error);
    }
  }, []);

  useEffect(() => {
    fetchInstruments(buildQueryParams(getValues(), currentPage));
  }, [buildQueryParams, currentPage, fetchInstruments, getValues]);

  const onSubmit = async (data: InstrumentSearchCriteria) => {
    setCurrentPage(0);
    await fetchInstruments(buildQueryParams(data, 0));
  };

  const setValueAsNumber = useCallback((value: string) => {
    return Number(value);
  }, []);

  return (
    <div>
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="bg-white rounded-lg shadow p-6 mb-6 flex flex-wrap items-end gap-4"
      >
        <div className="flex flex-wrap gap-4 flex-auto">
          {activeTab === "MUTUAL_FUND" ? (
            <>
              <label className="flex flex-col flex-1 min-w-[200px]">
                <span className="text-sm font-medium text-gray-700">
                  Funds Name
                </span>
                <input
                  {...register("name")}
                  type="text"
                  placeholder="Enter name"
                  className="mt-1 border border-[var(--color-primary)] rounded px-3 py-2 w-full h-10"
                />
              </label>
              <label className="flex flex-col flex-1 min-w-[200px]">
                <span className="text-sm font-medium text-gray-700">
                  Account Number
                </span>
                <input
                  {...register("accountNumber")}
                  type="text"
                  placeholder="Enter account #"
                  className="mt-1 border border-[var(--color-primary)] rounded px-3 py-2 w-full h-10"
                />
              </label>
            </>
          ) : (
            <>
              <label className="flex flex-col flex-1 min-w-[200px]">
                <span className="text-sm font-medium text-gray-700">
                  Fixed Income Name
                </span>
                <input
                  {...register("name")}
                  type="text"
                  placeholder="Enter name"
                  className="mt-1 border border-[var(--color-primary)] rounded px-3 py-2 w-full h-10"
                />
              </label>
              <label className="flex flex-col flex-1 min-w-[200px]">
                <span className="text-sm font-medium text-gray-700">
                  Interest Rate (%)
                </span>
                <input
                  {...register("interestRate", {
                    setValueAs: setValueAsNumber,
                  })}
                  type="number"
                  min={0}
                  placeholder="e.g. 3.25"
                  className="mt-1 border border-[var(--color-primary)] rounded px-3 py-2 w-full h-10"
                />
              </label>
            </>
          )}

          <label className="flex flex-col flex-1 min-w-[200px]">
            <span className="text-sm font-medium text-gray-700">Timeframe</span>
            <select
              {...register("months", {
                setValueAs: setValueAsNumber,
              })}
              className="mt-1 border border-[var(--color-primary)] rounded px-3 py-2 w-full h-10"
            >
              <option value={3}>3 months</option>
              <option value={6}>6 months</option>
              {activeTab === "FIXED_INCOME" && (
                <option value={9}>9 months</option>
              )}
            </select>
          </label>
        </div>

        <div className="flex gap-2">
          <button
            type="submit"
            disabled={isSubmitting}
            className="px-4 py-2 bg-[var(--color-secondary)] text-white rounded"
          >
            Search
          </button>
          <button
            type="reset"
            onClick={() => {
              reset();
            }}
            disabled={isSubmitting}
            className="px-4 py-2 border border-[var(--color-primary)] text-[var(--color-primary)] rounded hover:bg-gray-100"
          >
            Reset
          </button>
        </div>
      </form>

      {/* Instruments Table */}
      <div className="bg-white rounded-lg shadow overflow-x-auto">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Name
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Account Number
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Status
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Created At
              </th>
              {activeTab === "FIXED_INCOME" && (
                <>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Maturity Date
                  </th>
                  {/* <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Interest Rate
                  </th> */}
                </>
              )}
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {instruments.map((inst) => (
              <tr key={inst.id}>
                <td className="px-6 py-4 whitespace-nowrap">{inst.name}</td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {inst.accountNumber}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">{inst.status}</td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {formatDateTime(new Date(inst.createdAt))}
                </td>
                {activeTab === "FIXED_INCOME" && (
                  <>
                    <td className="px-6 py-4 whitespace-nowrap">
                      {inst.maturityDate
                        ? formatDateToDDMMYYYY(new Date(inst.maturityDate))
                        : "-"}
                    </td>
                    {/* <td className="px-6 py-4 whitespace-nowrap">
                      {inst.interestRate ? inst.interestRate : "-"}
                    </td> */}
                  </>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {/* Pagination controls */}
      <div className="mt-4 flex items-center justify-between px-4 py-3 bg-white border-t border-gray-200 sm:px-6">
        <div className="flex items-center text-sm text-gray-700">
          Showing {instruments.length ? currentPage * 10 + 1 : 0} to{" "}
          {Math.min((currentPage + 1) * 10, totalElements)} of {totalElements}{" "}
          results
        </div>
        <div className="flex items-center gap-2">
          <button
            onClick={() => {
              const newPage = currentPage - 1;
              setCurrentPage(newPage);
            }}
            disabled={currentPage === 0}
            className="px-3 py-1 border rounded text-sm font-medium disabled:opacity-50"
          >
            Previous
          </button>
          <span className="text-sm text-gray-700">
            Page {currentPage + 1} of {totalPages}
          </span>
          <button
            onClick={() => {
              const newPage = currentPage + 1;
              setCurrentPage(newPage);
            }}
            disabled={currentPage >= totalPages - 1}
            className="px-3 py-1 border rounded text-sm font-medium disabled:opacity-50"
          >
            Next
          </button>
        </div>
      </div>
    </div>
  );
};

export default FundSearch;
