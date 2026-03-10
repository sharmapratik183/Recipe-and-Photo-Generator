package com.springai.photogenerator.controller;

import com.springai.photogenerator.service.ChatService;
import com.springai.photogenerator.service.ImageService;
import com.springai.photogenerator.service.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

//    @GetMapping("/generate-image")
//    public void generateImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
//        ImageResponse imageResponse=imageService.generateImage(prompt);
//        String imageUrl=imageResponse.getResult().getOutput().getUrl();
//        response.sendRedirect(imageUrl);
//    }

    @GetMapping("/generate-image")
    public List<String> generateImages(@RequestParam String prompt,@RequestParam(defaultValue = "1") int count){
        List<ImageResponse> list=imageService.generateImage(prompt,count);
        List<String> imageUrls=list.stream()
                .map(result->result.getResult().getOutput().getUrl())
                .toList();
        return imageUrls;
    }

    @GetMapping("/recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "") String dietaryRestrictions)
    {
        return recipeService.createRecipe(ingredients,cuisine,dietaryRestrictions);
    }


}
