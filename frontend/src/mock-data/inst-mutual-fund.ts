import { Instrument } from "../types/Instrument";

export const mockMutualFund: Instrument[] = [
  {
    id: "11111111-1111-1111-1111-111111111111",
    name: "Real Estate Fund",
    accountNumber: "TI‑600006051",
    status: "ACTIVE",
    createdAt: "2025-01-15T10:30:00Z",
    maturityDate: undefined,
    interestRate: undefined,
    instrumentType: "MUTUAL_FUND",
  },
  {
    id: "22222222-2222-2222-2222-222222222222",
    name: "Tech Index Fund",
    accountNumber: "TI‑600006052",
    status: "ACTIVE",
    createdAt: "2025-02-01T14:45:00Z",
    maturityDate: undefined,
    interestRate: undefined,
    instrumentType: "MUTUAL_FUND",
  },
];
