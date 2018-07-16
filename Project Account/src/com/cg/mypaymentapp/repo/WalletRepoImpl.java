package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.util.ConnectionSQL;

public class WalletRepoImpl implements WalletRepo {

	private Map<String, Customer> data = new HashMap<>();

	public WalletRepoImpl(Map<String, Customer> data) {
		super();

	}

	@Override
	public boolean save(Customer customer) {
		if (customer == null||data.containsKey(customer.getMobileNo())) {
			return false;
		} 
		else {
			try {
				String query="insert into customer values(?,?,?)";
				PreparedStatement preparedStatement=ConnectionSQL.connection.prepareStatement(query);
				preparedStatement.setString(1, customer.getName());
				preparedStatement.setString(2, customer.getMobileNo());
				preparedStatement.setInt(3, customer.getWallet().getBalance().intValue());
				preparedStatement.execute();
				}
			catch (SQLException e) {
				e.printStackTrace();
				}
			return true;
			}
	}

	@Override
	public Customer findOne(String mobileNo) throws SQLException {

		Customer customer = new Customer();
		String query = "select * from customer where mobileNo=?";
		PreparedStatement preparedStatement=ConnectionSQL.connection.prepareStatement(query);
		preparedStatement.setString(1, mobileNo);
		ResultSet rs=preparedStatement.executeQuery();
		
		while (rs.next()) {
			if (rs.getString(2).equals(mobileNo))
			{
				customer.setName(rs.getString(1));
				customer.setMobileNo(rs.getString(2));
				BigDecimal b = new BigDecimal(rs.getInt(3));
				customer.setWallet(new Wallet(b));
			}
		}
		return customer;
	}

	@Override
	public void update(Customer customer) throws SQLException {

		String query = "update customer set balance=? where mobileNo=?";
		PreparedStatement preparedStatement=ConnectionSQL.connection.prepareStatement(query);
		preparedStatement.setInt(1, customer.getWallet().getBalance().intValue());
		preparedStatement.setString(2, customer.getMobileNo());
		
		preparedStatement.executeUpdate();
		
	}

}