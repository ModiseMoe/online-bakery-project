package za.co.mie.controller;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoOrderImpl;
import za.co.mie.bakeryService.BakeryServiceOrderImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.OrderItem;

@WebServlet(name = "InvoicePDF", urlPatterns = {"/InvoicePDF"})
public class InvoicePDF extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceOrderImpl bsoi = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));

        // Set the content type to PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.addTitle("Order Receipt");

            // Set up fonts
            Font headingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            Font datetimeFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph heading = new Paragraph("Order Receipt", headingFont);
            Paragraph datetime = new Paragraph(getCurrentDateTime(), datetimeFont);
            datetime.setSpacingAfter(10f);
            document.add(heading);
            document.add(datetime);

            // Create the table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Set table headers
            String[] headers = {"Product title", "Quantity", "Price", "Total", "Order ID"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, contentFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Add table rows with data
            List<OrderItem> orderItems = bsoi.getAllLineItems(43);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            double subtotal = 0.0;
            double taxRate = 0.15;
            double taxAmount = 0.0;
            double total = 0.0;
            int orderId = 0;

            for (OrderItem orderItem : orderItems) {
                double productTotal = orderItem.getUnitPrice() * orderItem.getProductQuantity();
                subtotal += productTotal;
                taxAmount = subtotal * taxRate;
                total = subtotal + taxAmount;
                orderId = orderItem.getOrder_id();

                table.addCell(orderItem.getProduct_title());
                table.addCell(String.valueOf(orderItem.getProductQuantity()));
                table.addCell("R" + decimalFormat.format(orderItem.getUnitPrice()));
                table.addCell("R" + decimalFormat.format(productTotal));
                table.addCell("#" + orderItem.getOrder_id());
            }

            // Add the table to the document
            document.add(table);

            // Add subtotal, tax, and total amount
            Paragraph amountParagraph = new Paragraph();
            amountParagraph.setAlignment(Element.ALIGN_RIGHT);
            amountParagraph.add(new Chunk("Subtotal: ", contentFont));
            amountParagraph.add(new Chunk("R" + decimalFormat.format(subtotal), contentFont));
            amountParagraph.add(Chunk.NEWLINE);
            amountParagraph.add(new Chunk("Tax: ", contentFont));
            amountParagraph.add(new Chunk("R" + decimalFormat.format(taxAmount), contentFont));
            amountParagraph.add(Chunk.NEWLINE);
            amountParagraph.add(new Chunk("Total: ", contentFont));
            amountParagraph.add(new Chunk("R" + decimalFormat.format(total), contentFont));
            document.add(amountParagraph);

            // Close the Document
            document.close();

            System.out.println("Invoice PDF generated successfully!");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    // Helper method to get the current date and time
    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Date and Time: " + dateFormat.format(new Date());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
