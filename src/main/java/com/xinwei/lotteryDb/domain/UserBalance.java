package com.xinwei.lotteryDb.domain;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.xinwei.lotteryDb.Const.UserBalanceApplyConst;

/**
 * Model class of user_balance.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class UserBalance implements Serializable{


	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 用户的UID. */
	private long userId;

	/** 用户的电话号码. */
	private String telphonenum;

	/** 操作前余额.更新数据库余额之前是当前数据库余额，更新数据库后为当前余额即更新后余额 */
	private double balance;

	/** 余额有效期. */
	private Date expiredata;

	/** 扩展余额信息. */
	private String balanceext;

	
	/** 老的扩展余额信息.用来更新校验使用，不需要数据库中存在该字段 */
	private String oldBalanceext;
	
	/** 事物号. */
	private String transaction;

	/** 最后更新时间. */
	private Date updatetime=Calendar.getInstance().getTime();

	/** 更新来源. */
	private String updatesource;

	/**
	 * 更新的余额
	 */
	private double amount;
	
	/**
	 * Constructor.
	 */
	public UserBalance() {
	}

	/**
	 * Set the 用户的UID.
	 * 
	 * @param userId
	 *            用户的UID
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public void setUserIdExist()
	{
		setUserId(-1);
	}
	public boolean isUserIDExist()
	{
	   return userId!=-1;
	}

	/**
	 * Get the 用户的UID.
	 * 
	 * @return 用户的UID
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Set the 用户的电话号码.
	 * 
	 * @param telphonenum
	 *            用户的电话号码
	 */
	public void setTelphonenum(String telphonenum) {
		this.telphonenum = telphonenum;
	}

	/**
	 * Get the 用户的电话号码.
	 * 
	 * @return 用户的电话号码
	 */
	public String getTelphonenum() {
		return this.telphonenum;
	}

	/**
	 * Set the 操作前余额.
	 * 
	 * @param beginningbalance
	 *            操作前余额
	 */
	public void setBalance(double balance) {
		this.balance =balance;
	}
	
	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Get the 操作前余额.
	 * 
	 * @return 操作前余额
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Set the 余额有效期.
	 * 
	 * @param expiredata
	 *            余额有效期
	 */
	public void setExpiredata(Date expiredata) {
		Calendar expireCalendar = Calendar.getInstance();
		expireCalendar.setTime(expiredata);
		expireCalendar.set(Calendar.HOUR_OF_DAY, 0);
		expireCalendar.set(Calendar.MINUTE, 0);
		expireCalendar.set(Calendar.SECOND, 0);
		expireCalendar.set(Calendar.MILLISECOND, 0);		
		this.expiredata = expireCalendar.getTime();
	}

	/**
	 * Get the 余额有效期.
	 * 
	 * @return 余额有效期
	 */
	public Date getExpiredata() {
		return this.expiredata;
	}

	/**
	 * Set the 扩展余额信息.
	 * 
	 * @param balanceext
	 *            扩展余额信息
	 */
	public void setBalanceext(String balanceext) {
		this.balanceext = balanceext;
	}

	/**
	 * Get the 扩展余额信息.
	 * 
	 * @return 扩展余额信息
	 */
	public String getBalanceext() {
		return this.balanceext;
	}

	
	
	
	public String getOldBalanceext() {
		return oldBalanceext;
	}

	public void setOldBalanceext(String oldBalanceext) {
		this.oldBalanceext = oldBalanceext;
	}

	/**
	 * Set the 事物号.
	 * 
	 * @param transaction
	 *            事物号
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	/**
	 * Get the 事物号.
	 * 
	 * @return 事物号
	 */
	public String getTransaction() {
		return this.transaction;
	}

	/**
	 * Set the 最后更新时间.
	 * 
	 * @param updatetime
	 *            最后更新时间
	 */
	public void setUpdatetime(Date updatetime) {
		Calendar expireCalendar = Calendar.getInstance();
		expireCalendar.setTime(updatetime);
		expireCalendar.set(Calendar.MILLISECOND, 0);		
		this.updatetime = expireCalendar.getTime();
	}

	/**
	 * Get the 最后更新时间.
	 * 
	 * @return 最后更新时间
	 */
	public Date getUpdatetime() {
		return this.updatetime;
	}

	/**
	 * Set the 更新来源.
	 * 
	 * @param updatesource
	 *            更新来源
	 */
	public void setUpdatesource(String updatesource) {
		this.updatesource = updatesource;
	}

	/**
	 * Get the 更新来源.
	 * 
	 * @return 更新来源
	 */
	public String getUpdatesource() {
		return this.updatesource;
	}
	/**
	 * 对同一个用户检查余额是否相等
	 * @param srcUserBalance
	 * @return 0--相等，其余数值，返回错误
	 */
	public int eqaulBalanceOneUser(UserBalance srcUserBalance)
	{
		
		if(this.getUserId()!=srcUserBalance.getUserId())
		{
			return UserBalanceApplyConst.ERROR_BALANCE_UID_NOTEQUAL;
		}
		if(!this.getTransaction().equalsIgnoreCase(srcUserBalance.getTransaction()))
		{
			return UserBalanceApplyConst.ERROR_TRANSACTION_NOTEQUAL; 
		}
		
		if(this.balance!=srcUserBalance.getBalance())
		{
			return UserBalanceApplyConst.ERROR_BALANCE_NOTEQUAL;
		}
		
		if(this.getUpdatetime().compareTo(srcUserBalance.getUpdatetime())!=0)
		{
			return UserBalanceApplyConst.ERROR_UPDATETIME_NOTEQUAL; 
		}
		if(this.getExpiredata().compareTo(srcUserBalance.getExpiredata())!=0)
		{
			return UserBalanceApplyConst.ERROR_EXPIRETIME_NOTEQUAL; 
		}
		
		return 0;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}
