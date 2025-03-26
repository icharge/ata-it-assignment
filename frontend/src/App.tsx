import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import Layout from "./components/Layout";
import InstrumentPage from "./pages/instruments";
import FundSearch from "./pages/instruments/FundSearch";

function App() {
  return (
    <BrowserRouter>
      <Layout>
        <Routes>
          <Route path="/" element={<InstrumentPage />}>
            <Route
              index
              element={<Navigate to="instruments/MUTUAL_FUND" replace />}
            />
            <Route
              path="instruments/MUTUAL_FUND"
              element={<FundSearch key="mf" instrumentType="MUTUAL_FUND" />}
            />
            <Route
              path="instruments/FIXED_INCOME"
              element={<FundSearch key="fi" instrumentType="FIXED_INCOME" />}
            />
            <Route
              path="*"
              element={<div className="text-center py-20">Page not found</div>}
            />
          </Route>
        </Routes>
      </Layout>
    </BrowserRouter>
  );
}

export default App;
