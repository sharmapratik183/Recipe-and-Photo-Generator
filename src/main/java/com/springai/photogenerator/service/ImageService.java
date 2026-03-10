package com.springai.photogenerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public List<ImageResponse> generateImage(String prompt, int count){
//        ImageResponse imageResponse=openAiImageModel.call(
//                new ImagePrompt(prompt)
//        );
//        return imageResponse;

        List<ImageResponse> imageResponses=new ArrayList<>();
        for(int i=1;i<=count;i++) {
            ImageResponse imageResponse = openAiImageModel.call(
                    new ImagePrompt(prompt,
                            OpenAiImageOptions.builder()
                                    .quality("hd")
                                    .N(1)
                                    .height(1024)
                                    .width(1024).build())
            );
            imageResponses.add(imageResponse);
        }
        return imageResponses;
    }
}
