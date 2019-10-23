package com.qhc.bayern.controller.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SapOrderPrice {
	// Item/行项目编号 -- Selling tool 行号
	private Integer posnr;
	// Condition type/价格类型	-- Selling tool 实卖价合计(Item)/折扣（Header）
	private String kschl;
	// Rate/金额
	private BigDecimal kbetr;
	
	public Integer getPosnr() {
		return posnr;
	}
	public void setPosnr(Integer posnr) {
		this.posnr = posnr;
	}
	public String getKschl() {
		return kschl;
	}
	public void setKschl(String kschl) {
		this.kschl = kschl;
	}
	public BigDecimal getKbetr() {
		return kbetr;
	}
	public void setKbetr(BigDecimal kbetr) {
		this.kbetr = kbetr;
		if (this.kbetr != null) {
			this.kbetr = this.kbetr.setScale(2, RoundingMode.HALF_UP);
		}
	}
}