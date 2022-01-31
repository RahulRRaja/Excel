package com.sample.Spring.boot.excel.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import com.sample.Spring.boot.excel.Export.ExcelFileExporter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import com.sample.Spring.boot.excel.model.Customer;
import java.util.List;
import java.util.ArrayList;

public class DownloadExcelController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/download/customers.xlsx")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(createTestData());
        IOUtils.copy(stream, response.getOutputStream());
    }

    private List<Customer> createTestData(){
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("Vernon", "Barlow", "0123456789", "test1@simplesolution.dev"));
        customers.add(new Customer("Maud", "Brock", "0123456788", "test2@simplesolution.dev"));
        customers.add(new Customer("Chyna", "Cowan", "0123456787", "test3@simplesolution.dev"));
        customers.add(new Customer("Krisha", "Tierney", "0123456786", "test4@simplesolution.dev"));
        customers.add(new Customer("Sherry", "Rosas", "0123456785", "test5@simplesolution.dev"));
        return customers;
    }
}
