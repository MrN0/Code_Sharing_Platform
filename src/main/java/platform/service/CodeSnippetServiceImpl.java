package platform.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import platform.dto.CodeSnippetDTO;
import platform.dto.CodeSnippetIdDTO;
import platform.entity.CodeSnippet;
import platform.exception.CodeSnippetNotFoundException;
import platform.repository.CodeSnippetRepository;

@Service
public class CodeSnippetServiceImpl implements CodeSnippetService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final CodeSnippetRepository repository;

    @Autowired
    public CodeSnippetServiceImpl(CodeSnippetRepository repository) {
        this.repository = repository;
    }

    @Override
    public CodeSnippetIdDTO postCodeSnippet(CodeSnippetDTO codeSnippetDTO) {
        CodeSnippet snippet = repository.save(getEntityFromDTO(codeSnippetDTO));
        return new CodeSnippetIdDTO(snippet.getId());
    }

    @Override
    public List<CodeSnippetDTO> getLatest10CodeSnippets() {
        List<CodeSnippet> codeSnippets = repository.findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(0, 0);
        List<CodeSnippetDTO> codeSnippetDTOS = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSnippets) {
            codeSnippetDTOS.add(getDTOFromEntity(codeSnippet));
        }
        return codeSnippetDTOS;
    }

    @Override
    public CodeSnippetDTO getCodeSnippetById(CodeSnippetIdDTO codeSnippetIdDTO) {
        Optional<CodeSnippet> optionalCodeSnippet = repository.findById(codeSnippetIdDTO.getId());
        if (optionalCodeSnippet.isPresent()) {
            var codeSnippet = optionalCodeSnippet.get();
            var codeSnippetDTO = getDTOFromEntity(codeSnippet);

            if (!codeSnippetDTO.expired()) {
                if (codeSnippetDTO.isViewsLimited()) {
                    long views = codeSnippet.getViews() > 0 ? codeSnippet.getViews() - 1 : 0;
                    if (views == 0) {
                        repository.delete(codeSnippet);
                    } else {
                        codeSnippet.setViews(views);
                        repository.save(codeSnippet);
                    }
                    codeSnippetDTO.setViews(views);
                }
                return codeSnippetDTO;
            }
        }

        throw new CodeSnippetNotFoundException(
                "Cannot find code snippet with id: " + codeSnippetIdDTO.getId().toString());
    }

    private CodeSnippet getEntityFromDTO(CodeSnippetDTO codeSnippetDTO) {
        var codeSnippet = new CodeSnippet();
        codeSnippet.setCode(codeSnippetDTO.getCode());
        codeSnippet.setDate(LocalDateTime.now());
        codeSnippet.setTime(codeSnippetDTO.getTime());
        codeSnippet.setViews(codeSnippetDTO.getViews());
        return codeSnippet;
    }

    private CodeSnippetDTO getDTOFromEntity(CodeSnippet codeSnippet) {
        var codeSnippetDTO = new CodeSnippetDTO();
        codeSnippetDTO.setCode(codeSnippet.getCode());
        codeSnippetDTO.setDate(codeSnippet.getDate().format(FORMATTER));
        codeSnippetDTO.setTime(calculateRestOfTime(codeSnippet.getDate(), codeSnippet.getTime()));
        codeSnippetDTO.setViews(codeSnippet.getViews());
        codeSnippetDTO.setTimeLimited(codeSnippet.getTime() != 0);
        codeSnippetDTO.setViewsLimited(codeSnippet.getViews() != 0);
        return codeSnippetDTO;
    }

    private long calculateRestOfTime(LocalDateTime date, long time) {
        if (time == 0) {
            return 0;
        }
        long seconds = Duration.between(LocalDateTime.now(), date.plusSeconds(time)).getSeconds();
        return seconds > 0 ? seconds : 0;
    }

}
