package com.sna.email.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_token")
public class CustomerToken {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long customerTokenId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "token_type")
	private String tokenType;

	@Column(name = "status")
	private Long status;

	@Column(name = "level_id")
	private String levelId;

	@Column(name = "issue_date")
	private Date issueDate;

	@Column(name = "expiry_date")
	private Date expiryDate;

	public long getCustomerTokenId() {
		return customerTokenId;
	}

	public void setCustomerTokenId(long customerTokenId) {
		this.customerTokenId = customerTokenId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}


	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}