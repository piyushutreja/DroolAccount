package com.sample.entities;

import java.text.DateFormat;
import java.util.Date;

public class CashFlow {
	
	private Date mvDAte; 
	private double amount; 
	private int	type; 
	private long accountNumber;
	public static int CREDIT = 1;
    public static int DEBIT = 2;
	
	public Date getMvDAte() {
		return mvDAte;
	}
	public void setMvDAte(Date mvDAte) {
		this.mvDAte = mvDAte;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuffer buff = new StringBuffer();
        buff.append("-----CashFlow-----)\n");
        buff.append("Account no=" + this.accountNumber + "\n");
        if (this.mvDAte != null) {
            buff.append("Mouvement Date= "
                    + DateFormat.getDateInstance().format(this.mvDAte)
                    + "\n");
        } else {
            buff.append("No Mouvement date was set\n");	
        }
        buff.append("Mouvement Amount=" + this.amount + "\n");
        buff.append("-----CashFlow end--)");
        return buff.toString();
    }
	
}
