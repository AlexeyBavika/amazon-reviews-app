package service;

import static org.junit.Assert.*;
import com.example.amazon.reviews.mapper.ReviewMapper;
import com.example.amazon.reviews.model.dto.ReviewDto;
import com.example.amazon.reviews.service.CsvService;
import com.example.amazon.reviews.service.FileService;
import com.example.amazon.reviews.service.impl.CsvServiceImpl;
import com.example.amazon.reviews.service.impl.FileServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CsvServiceImplTest {
    private static final String TEST_FILE_PATH = "src/test/resources/testCsv";
    private static final int EXPECTED_NUMBER_OF_RECORDS = 10;
    private static List<String> testFileContentList;

    @BeforeAll
    public static void initializeList() {
        FileService fileService = new FileServiceImpl();
        testFileContentList = fileService.readFile(TEST_FILE_PATH);
    }

    @Test
    public void shouldContainTenRecords() {
        CsvService service = new CsvServiceImpl(new ReviewMapper());
        List<ReviewDto> list = service.parseLinesToGetReviewDto(testFileContentList);
        assertEquals(EXPECTED_NUMBER_OF_RECORDS, list.size());
    }
}