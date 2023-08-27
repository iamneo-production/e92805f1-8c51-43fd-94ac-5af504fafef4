package com.billing.payment.hackthon.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import com.billing.payment.hackthon.constants.BillingPaymentContants;
import com.billing.payment.hackthon.dto.BillingDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoicePdfGenerator {

	public static ByteArrayInputStream invoicePDFReport(List<BillingDTO> invoices) {
		double total = 0.0;
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Adding Text to PDF file
			Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 25, BaseColor.BLUE);
			Paragraph para = new Paragraph("Medical Billing Invoice", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable invoiceHeader = new PdfPTable(2);
			Stream.of(BillingPaymentContants.INVOICE_ID + getnerateInvoiceId(),
					BillingPaymentContants.INVOICE_DATE
							+ new SimpleDateFormat(BillingPaymentContants.INVOICE_DATE_FORMAT).format(new Date()))
					.forEach(t -> {
						PdfPCell header = new PdfPCell();
						header.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						header.setBorderWidth(0);
						header.setPhrase(new Phrase(t));
						invoiceHeader.addCell(header);
					});

			document.add(invoiceHeader);

			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(5);
			// Add PDF Table for Header
			Stream.of(BillingPaymentContants.IN_PATIENT_ID, BillingPaymentContants.PATIENT_NAME,
					BillingPaymentContants.ITEM, BillingPaymentContants.DESCRIPTION, BillingPaymentContants.AMOUNT)
					.forEach(headerTitle -> {
						PdfPCell header = new PdfPCell();
						Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
						header.setBackgroundColor(BaseColor.LIGHT_GRAY);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.setBorderWidth(1);
						header.setPhrase(new Phrase(headerTitle, headFont));
						table.addCell(header);
					});

			for (BillingDTO invoice : invoices) {
				PdfPCell patientId = new PdfPCell(new Phrase(invoice.getPatientId()));
				patientId.setPaddingLeft(4);
				patientId.setVerticalAlignment(Element.ALIGN_MIDDLE);
				patientId.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(patientId);

				PdfPCell patientName = new PdfPCell(new Phrase(invoice.getPatientName()));
				patientName.setPaddingLeft(4);
				patientName.setVerticalAlignment(Element.ALIGN_MIDDLE);
				patientName.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(patientName);

				PdfPCell item = new PdfPCell(new Phrase(String.valueOf(invoice.getItem())));
				item.setVerticalAlignment(Element.ALIGN_MIDDLE);
				item.setHorizontalAlignment(Element.ALIGN_LEFT);
				item.setPaddingRight(4);
				table.addCell(item);

				PdfPCell description = new PdfPCell(new Phrase(String.valueOf(invoice.getDescription())));
				description.setVerticalAlignment(Element.ALIGN_LEFT);
				description.setHorizontalAlignment(Element.ALIGN_LEFT);
				description.setPaddingRight(4);
				table.addCell(description);

				PdfPCell amount = new PdfPCell(new Phrase(String.valueOf(invoice.getAmount())));
				amount.setVerticalAlignment(Element.ALIGN_MIDDLE);
				amount.setHorizontalAlignment(Element.ALIGN_RIGHT);
				amount.setPaddingRight(4);
				table.addCell(amount);
				total += Objects.nonNull(invoice.getAmount()) ? Double.parseDouble(invoice.getAmount()) : 0.0;
			}

			document.add(table);
			document.add(Chunk.NEWLINE);

			PdfPTable totalAmount = new PdfPTable(1);
			Stream.of("Total Amount: " + new DecimalFormat("0.00").format(total)).forEach(t -> {
				PdfPCell header = new PdfPCell();
				header.setHorizontalAlignment(Element.ALIGN_RIGHT);
				header.setBorderWidth(0);
				header.setPhrase(new Phrase(t));
				totalAmount.addCell(header);
			});

			document.add(totalAmount);
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private static String getnerateInvoiceId() {
		Random random = new Random();
		return "xxxxx-xxxxx-xx" + String.format("%04d", random.nextInt(10000));
	}
}
