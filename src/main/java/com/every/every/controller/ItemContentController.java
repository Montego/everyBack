package com.every.every.controller;

import com.every.every.dto.ItemContentDTO;
import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;
import com.every.every.service.entityService.ItemContentService;
import com.every.every.service.entityService.JasperService;
import com.every.every.service.entityService.TreeStoreService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.every.every.dto.Converter.convertItemContentDTOToItemContent;

//import com.every.every.service.entityService.ItemContentService;

@RestController
@RequestMapping("/itemContent")
public class ItemContentController {
    private final ItemContentService itemContentService;
    private final TreeStoreService treeStoreService;
    private final JasperService jasperService;

    @Autowired
    public ItemContentController(ItemContentService itemContentService, TreeStoreService treeStoreService, JasperService jasperService) {
        this.itemContentService = itemContentService;
        this.treeStoreService = treeStoreService;
        this.jasperService = jasperService;
    }

    @PutMapping("/editItemContent/{id}")
    public String editItemContent(@PathVariable String id, @RequestBody ItemContentDTO itemContentDTO) {
        TreeStore treeStore = treeStoreService.getOne(id);
        ItemContent itemContentFromDB = treeStore.getData();
        ItemContent itemContentNew = convertItemContentDTOToItemContent(itemContentDTO);

        itemContentNew.setId(itemContentFromDB.getId());
        itemContentNew.setType(itemContentFromDB.getType());
        itemContentNew.setTreeStore(treeStore);
        treeStore.setData(itemContentNew);

//        itemContentService.save(itemContentNew);
        treeStoreService.save(treeStore);
        return "update itemContent";
    }

    @GetMapping("/getOne/{id}")
    public ItemContent getItemContent(@PathVariable String id) {
        return treeStoreService.getOne(id).getData();
    }

    @GetMapping("/getOneItem/{id}")
    public void getFile(@PathVariable String id, HttpServletResponse response) throws IOException, JRException {
        ItemContent currFile = treeStoreService.getOne(id).getData();
        String content = currFile.getContent();
        String fileName = currFile.getContentName();
//TODO доделать - возвращение pdf
        String contentAfter = content.replaceAll("data:application/pdf;base64,","");
        byte[] data = Base64.decodeBase64(contentAfter);
        response.setContentType("application/pdf");
        response.setHeader("content-disposition", "inline; filename =" + fileName);

//        OutputStream out = new FileOutputStream("fileName");
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.close();

//        JRPdfExporter exporter = new JRPdfExporter();
//        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response));
//        exporter.exportReport();

//        jasperService.print(out, id, user);

    }


}
