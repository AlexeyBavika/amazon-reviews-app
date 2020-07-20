package service;

import com.example.amazon.reviews.service.FileService;
import static org.junit.Assert.*;

import com.example.amazon.reviews.service.impl.FileServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class FileServiceImplTest {
    private static final String TEST_FILE_PATH = "src/test/resources/testCsv";
    private static final int EXPECTED_NUMBER_OF_ROWS = 11;
    private static FileService fileService;

    @BeforeAll
    public static void initialize() {
        fileService = new FileServiceImpl();
    }

    @Test
    public void shouldContainElevenRows() {
        List<String> list = fileService.readFile(TEST_FILE_PATH);
        assertEquals(EXPECTED_NUMBER_OF_ROWS, list.size());
    }
}