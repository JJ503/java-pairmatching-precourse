package pairmatching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnterCrewFileTest {
    EnterCrewFile enterCrewFile = new EnterCrewFile();

    @DisplayName("파일 읽기 및 List 형 반환 정상 동작 테스트")
    @MethodSource("generatePathData")
    @ParameterizedTest
    void enterNormalPathTest(String path, List<String> expect) throws IOException {
        assertThat(enterCrewFile.enterFileToList(path)).isEqualTo(expect);
    }

    static Stream<Arguments> generatePathData() {
        return Stream.of(
                Arguments.of("src/main/resources/frontend-crew.md", Arrays.asList("보노", "시저", "쉐리", "신디",
                        "다비", "덴버", "이브", "제시", "라라", "린다", "리사", "니콜", "로드", "윌터", "제키")),
                Arguments.of("src/main/resources/backend-crew.md", Arrays.asList("백호", "태웅", "치수", "태섭",
                        "대만", "준호", "대협", "덕규", "태산", "경태", "수겸", "현준", "준섭", "한나", "소연", "호열", "대남", "용팔",
                        "구식", "달재"))
        );
    }

    @DisplayName("존재하지 않는 파일에 대한 예외 동작 테스트")
    @ValueSource(strings = {"src/main/resources/error-crew.md", "/src/main/resources/frontend-crew.md"})
    @ParameterizedTest
    void enterExceptionPathTest(String path) {
        assertThatThrownBy(() -> enterCrewFile.enterFileToList(path))
                .isInstanceOf(FileNotFoundException.class);
    }
}
