package sk.moonid.processor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryNoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn400WhenFileEmpty() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "", "image/jpeg", new byte[0]);

        mockMvc.perform(multipart("/api/delivery-notes/upload")
                        .file(emptyFile))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.statusText").value("Bad Request"))
                .andExpect(jsonPath("$.errorCode").value("FILE_EMPTY"))
                .andExpect(jsonPath("$.message").value("File is empty. Please upload a valid file."));
    }

    @Test
    void shouldReturn200WhenValidFile() throws Exception {
        byte[] fakeImageBytes = "fake JPEG content".getBytes();

        MockMultipartFile validFile = new MockMultipartFile(
                "file", "test_img.jpeg", "image/jpeg", fakeImageBytes
        );

        mockMvc.perform(multipart("/api/delivery-notes/upload")
                        .file(validFile))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filename").value("test_img.jpeg"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.uploadedAt").exists());
    }
}
