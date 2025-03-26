import { InstrumentType, InstrumentTypeDesc } from "../../types/Instrument";
import { NavLink, Outlet, useLocation } from "react-router-dom";

const InstrumentPage: React.FC = () => {
  const location = useLocation();
  console.log("location", location);
  return (
    <div>
      <header className="bg-white rounded-lg shadow-sm mb-4">
        <div className="mx-auto max-w-7xl px-4 py-4 sm:px-6 lg:px-8">
          <h1 className="text-2xl font-bold tracking-tight text-gray-900">
            Instruments
          </h1>
          <ul className="flex mt-4 -mb-2 gap-4">
            {[InstrumentType.MUTUAL_FUND, InstrumentType.FIXED_INCOME].map(
              (id) => {
                const isActive = location.pathname.includes(id);
                return (
                  <li key={id}>
                    <NavLink
                      to={`/instruments/${id}`}
                      role="tab"
                      aria-selected={isActive}
                      // onClick={() => setActiveTab(id as InstrumentTypeKey)}
                      className={`
            pb-2.5 text-base font-medium transition-colors
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

      <Outlet />
    </div>
  );
};

export default InstrumentPage;
