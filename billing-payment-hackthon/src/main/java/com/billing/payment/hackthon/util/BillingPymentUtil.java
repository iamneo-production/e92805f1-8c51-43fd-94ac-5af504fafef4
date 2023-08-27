package com.billing.payment.hackthon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.billing.payment.hackthon.constants.BillingPaymentContants;

public class BillingPymentUtil {

	public static Date toDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(BillingPaymentContants.DATE_FORMAT);
		Date rDate = null;
		try {
			rDate = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rDate;
	}
	
	public static String getDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(BillingPaymentContants.DATE_FORMAT);
		if (date != null) {
			return sdf.format(date);
		} else {
			return sdf.format(new Date());
		}
	}
	
	public static String generatePymtId() {
		return UUID.randomUUID().toString();
	}
}
