package br.furb.consultor.yahoo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.joda.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class AcaoBolsaYahoo{

    @XmlElement(name = "Ask")
	private double ask;
    
    @XmlElement(name = "Symbol")
	private String symbol;
    
    @XmlElement(name = "Name")
	private String name;
    
    @XmlElement(name = "AverageDailyVolume")
	private double averageDailyVolume;
    
    @XmlElement(name = "Change")
	private double change;
    
    @XmlElement(name = "PercentChange")
	private String percentChange;
    
    @XmlElement(name = "LastTradeDate")
	private LocalDate lastTradeDate;
    
    @XmlElement(name = "LastTradeTime")
	private String lastTradeTime;
    
    @XmlElement(name = "DaysLow")
	private double daysLow;
    
    @XmlElement(name = "DaysHigh")
	private double daysHigh;
    
    @XmlElement(name = "YearLow")
	private double yearLow;
    
    @XmlElement(name = "YearHigh")
	private double yearHigh;
    
    @XmlElement(name = "MarketCapitalization")
	private String marketCapitalization;
    
    @XmlElement(name = "EBITDA")
	private String ebitda;
    
    @XmlElement(name = "Open")
	private double open;
    
    @XmlElement(name = "LastTradePriceOnly")
	private double lastTradePriceOnly;
    
    @XmlElement(name = "StockExchange")
	private String stockExchange;

	public double getAsk() {
		return ask;
	}
	public void setAsk(double ask) {
		this.ask = ask;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAverageDailyVolume() {
		return averageDailyVolume;
	}
	public void setAverageDailyVolume(double averageDailyVolume) {
		this.averageDailyVolume = averageDailyVolume;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	public String getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}
	public LocalDate getLastTradeDate() {
		return lastTradeDate;
	}
	public void setLastTradeDate(LocalDate lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
	public String getLastTradeTime() {
		return lastTradeTime;
	}
	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}
	public double getDaysLow() {
		return daysLow;
	}
	public void setDaysLow(double daysLow) {
		this.daysLow = daysLow;
	}
	public double getDaysHigh() {
		return daysHigh;
	}
	public void setDaysHigh(double daysHigh) {
		this.daysHigh = daysHigh;
	}
	public double getYearLow() {
		return yearLow;
	}
	public void setYearLow(double yearLow) {
		this.yearLow = yearLow;
	}
	public double getYearHigh() {
		return yearHigh;
	}
	public void setYearHigh(double yearHigh) {
		this.yearHigh = yearHigh;
	}
	public String getMarketCapitalization() {
		return marketCapitalization;
	}
	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}
	public String getEBITDA() {
		return ebitda;
	}
	public void setEBITDA(String ebitda) {
		this.ebitda = ebitda;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getLastTradePriceOnly() {
		return lastTradePriceOnly;
	}
	public void setLastTradePriceOnly(double lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	
}
