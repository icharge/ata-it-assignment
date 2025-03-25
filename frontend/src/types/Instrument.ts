export const InstrumentTypeDesc = {
  MUTUAL_FUND: "Mutual Fund",
  FIXED_INCOME: "Fixed Income",
};

export const InstrumentType = {
  MUTUAL_FUND: "MUTUAL_FUND",
  FIXED_INCOME: "FIXED_INCOME",
};
export type InstrumentTypeKey = keyof typeof InstrumentTypeDesc;

export interface Instrument {
  id: string;
  name: string;
  accountNumber: string;
  instrumentType: InstrumentTypeKey;
  status: string;
  createdAt: string;
  // Fixed Income specific
  maturityDate?: string;
  interestRate?: number;
}
