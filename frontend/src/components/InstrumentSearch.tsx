import { useState, useEffect, useCallback } from "react";
import {
  Instrument,
  InstrumentType,
  InstrumentTypeDesc,
  InstrumentTypeKey,
} from "../types/Instrument";
import { mockMutualFund } from "../mock-data/inst-mutual-fund";
import { NavLink } from "react-router-dom";

interface InstrumentSearchCriteria {
  instrumentType?: InstrumentTypeKey;
  name?: string;
  accountNumber?: string;
  months?: number;
  interestRate?: number;
  maturityDate?: string;
  status?: string;
}

interface InstrumentSearchProps {
  instrumentType: InstrumentTypeKey;
}

const InstrumentSearch: React.FC<InstrumentSearchProps> = ({
  instrumentType,
}) => {
  const [activeTab, setActiveTab] = useState<InstrumentTypeKey>(instrumentType);
  const [criteria, setCriteria] = useState<InstrumentSearchCriteria>({});
  const [instruments, setInstruments] = useState<Instrument[]>(mockMutualFund);
  const [searchText, setSearchText] = useState("");

  // Build query params from criteria & activeTab
  const buildQueryParams = useCallback(() => {
    const params = new URLSearchParams();
    params.append("instrumentType", activeTab);
    if (searchText) {
      params.append("name", searchText);
    }
    // Add more criteria as needed (e.g., accountNumber, months)
    if (criteria.months) {
      params.append("months", criteria.months.toString());
    }
    if (criteria.status) {
      params.append("status", criteria.status);
    }
    return params.toString();
  }, [activeTab, criteria.months, criteria.status, searchText]);

  const fetchInstruments = useCallback(async () => {
    try {
      const queryParams = buildQueryParams();
      const response = await fetch(
        `http://localhost:8080/api/instruments/search?${queryParams}`
      );
      if (response.ok) {
        // Assuming the response is a paginated result with a "content" array
        const data = await response.json();
        setInstruments(data.content || data); // adapt based on your API response
      } else {
        console.error("Error fetching instruments:", response.statusText);
      }
    } catch (error) {
      console.error("Error fetching instruments:", error);
    }
  }, [buildQueryParams]);

  // Re-fetch instruments when activeTab or search criteria change
  useEffect(() => {
    fetchInstruments();
  }, [activeTab, criteria, fetchInstruments, searchText]);

  return (
    <div>
      <header className="bg-white rounded-lg shadow-sm mb-4">
        <div className="mx-auto max-w-7xl px-4 py-4 sm:px-6 lg:px-8">
          <h1 className="text-2xl font-bold tracking-tight text-gray-900">
            Instruments
          </h1>
          <ul className="flex mt-4 -mb-4 gap-4">
            {[InstrumentType.MUTUAL_FUND, InstrumentType.FIXED_INCOME].map(
              (id) => {
                const isActive = activeTab === id;
                return (
                  <li key={id}>
                    <NavLink
                      to={`/instruments/${id}`}
                      role="tab"
                      aria-selected={isActive}
                      // onClick={() => setActiveTab(id as InstrumentTypeKey)}
                      className={`
            pb-2 text-base font-medium transition-colors
            ${
              isActive
                ? "text-(--color-secondary) border-b-2 border-(--color-secondary)"
                : "text-gray-600 hover:text-(--color-secondary)"
            }
          `}
                    >
                      {
                        InstrumentTypeDesc[
                          id as keyof typeof InstrumentTypeDesc
                        ]
                      }
                    </NavLink>
                  </li>
                );
              }
            )}
          </ul>
        </div>
      </header>

      <div className="bg-white rounded-lg shadow p-6 mb-6 flex flex-wrap items-end gap-4">
        {/* LEFT: Dynamic filters */}
        <div className="flex flex-wrap gap-4 flex-auto">
          {activeTab === "MUTUAL_FUND" ? (
            <>
              <label className="flex flex-col flex-1 min-w-[200px]">
                <span className="text-sm font-medium text-gray-700">
                  Funds Name
                </span>
                <input
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
                  type="number"
                  placeholder="e.g. 3.25"
                  className="mt-1 border border-[var(--color-primary)] rounded px-3 py-2 w-full h-10"
                />
              </label>
            </>
          )}

          <label className="flex flex-col flex-1 min-w-[200px]">
            <span className="text-sm font-medium text-gray-700">Timeframe</span>
            <select className="mt-1 border border-[var(--color-primary)] rounded px-3 py-2 w-full h-10">
              <option value={3}>3 months</option>
              <option value={6}>6 months</option>
              {activeTab === "FIXED_INCOME" && (
                <option value={9}>9 months</option>
              )}
            </select>
          </label>
        </div>

        {/* RIGHT: Action buttons */}
        <div className="flex gap-2">
          <button className="px-4 py-2 bg-[var(--color-secondary)] text-white rounded hover:bg-[var(--color-secondary)/90]">
            Search
          </button>
          <button className="px-4 py-2 border border-[var(--color-primary)] text-[var(--color-primary)] rounded hover:bg-gray-100">
            Reset
          </button>
        </div>
      </div>

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
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Interest Rate
                  </th>
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
                  {new Date(inst.createdAt).toLocaleString()}
                </td>
                {activeTab === "FIXED_INCOME" && (
                  <>
                    <td className="px-6 py-4 whitespace-nowrap">
                      {inst.maturityDate
                        ? new Date(inst.maturityDate).toLocaleDateString()
                        : "-"}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      {inst.interestRate ? inst.interestRate : "-"}
                    </td>
                  </>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {/* Pagination controls can be added here */}
    </div>
  );
};

export default InstrumentSearch;
