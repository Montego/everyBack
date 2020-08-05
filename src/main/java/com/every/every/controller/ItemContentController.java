package com.every.every.controller;

import com.every.every.dto.ItemContentDTO;
import com.every.every.entity.ItemContent;
import com.every.every.entity.TreeStore;
import com.every.every.service.entityService.TreeStoreService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.every.every.dto.Converter.convertItemContentDTOToItemContent;


@RestController
@RequestMapping("/itemContent")
public class ItemContentController {
    private final TreeStoreService treeStoreService;

    public ItemContentController(TreeStoreService treeStoreService) {
        this.treeStoreService = treeStoreService;
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

        treeStoreService.save(treeStore);
        return "update itemContent";
    }

    @GetMapping("/getOne/{id}")
    public ItemContent getItemContent(@PathVariable String id) {
        return treeStoreService.getOne(id).getData();
    }

    @GetMapping("/getOneItem/{id}")
    public void getFile(@PathVariable String id, HttpServletResponse response) throws IOException {
        ItemContent currFile = treeStoreService.getOne(id).getData();
        String content = currFile.getContent();
        String fileName = currFile.getContentName();
        String contentAfter = content.replaceAll("data:application/pdf;base64,", "");
        byte[] data = Base64.decodeBase64(contentAfter);
        response.setContentType("application/pdf");
        response.setHeader("content-disposition", "inline; filename =" + fileName);
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.close();
    }


}
