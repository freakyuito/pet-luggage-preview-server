package com.overseachem.server.controller;

import com.overseachem.server.pojo.RenderItem;
import com.overseachem.server.service.PreviewService;
import com.overseachem.server.utils.AjaxReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/preview")
public class PreviewController {

    public static String imgUploadRoot = "/www/ftproot/preview/upload/";
    public static String renderOutputRoot = "/www/ftproot/preview/render/";
    public static String blenderExePath = "blender";
    public static String blenderFileRoot = "/home/documents/blender_process/blend/";
    public static String pythonFileRoot = "/home/documents/blender_process/python/";


    @Autowired
    private PreviewService previewService;

    @RequestMapping(value = "/index")
    public String IndexPage(Model model) {
        return "index";
    }


    @RequestMapping(value = "/gl")
    public String GLPage(HttpServletRequest request, @RequestParam("textureUrl") String textureUrl) {
        request.setAttribute("textureUrl", textureUrl);
        System.out.println("/preview/gl->send attr to model:textureUrl=" + textureUrl);
        return "gl";
    }

    @RequestMapping(value = "/upload_img")
    @ResponseBody
    public AjaxReturn UploadImg(@RequestParam("file") MultipartFile file) {
        String imgFileName = file.getOriginalFilename();
        System.out.println("/preview/upload_img->" + imgFileName);
        String imgUploadPath = imgUploadRoot + imgFileName;
        System.out.println("/preview/upload_img->prepare to call function [PreviewService->Upload]");
        if (previewService.upload("/www/ftproot/preview/upload", file)) {
            return new AjaxReturn(true, imgUploadPath);
        } else {
            return new AjaxReturn(false, "upload failed!");
        }
    }


    @RequestMapping(value = "/render")
    public String RenderPage(HttpServletRequest request, @RequestParam("uploadImgPath") String uploadImgPath) {

        ArrayList<RenderItem> renderItems = new ArrayList<>();
        renderItems.add(new RenderItem("front", "正面", "实景渲染正视图", ""));
        renderItems.add(new RenderItem("side", "侧面", "只为你的侧面", ""));
        renderItems.add(new RenderItem("back", "背面", "拉杆与箱体的紧密结合", ""));
        renderItems.add(new RenderItem("bar", "拉杆", "金属的光泽将整体效果提升了一个高度", ""));
        renderItems.add(new RenderItem("wheels", "跑轮", "经过苛刻测试的万向跑轮，为行李安全保驾护航", ""));
        renderItems.add(new RenderItem("shanghai_night", "都市夜景", "霓虹和夜色将本就独一无二的箱包衬托得更加独具一格", ""));
//
        String pythonName = "texture_change";
//
        String[] fullName = uploadImgPath.split("\\/");
        String[] temp = fullName[fullName.length - 1].split("\\.");
        String imgName = temp[0];
        String imgFormat = "." + temp[1];
//
        for (RenderItem renderItem : renderItems
                ) {
            String blenderFilePath = blenderFileRoot + renderItem.getAlias() + ".blend";
            String pythonFilePath = pythonFileRoot + pythonName + ".py";
            String renderOutputPath = renderOutputRoot + imgName + "-" + renderItem.getAlias() + imgFormat;
            String renderOutputUrl = "http://106.15.207.141/preview/render/" + imgName + "-" + renderItem.getAlias() + imgFormat;
            renderItem.setUrl(renderOutputUrl);
            previewService.renderByTexture(blenderExePath, blenderFilePath,
                    pythonFilePath, uploadImgPath, renderOutputPath);
            System.out.println("/preview/render->send attr to model:outputUrl=" + renderOutputPath);
        }
        request.setAttribute("renderItems", renderItems);
        String imgUrl = "http://106.15.207.141/preview/upload/" + imgName + imgFormat;
        request.setAttribute("imgUrl", imgUrl);
        System.out.println("/preview/render->send attr to model:imgUrl=" + imgUrl);

        return "render";
    }

}
