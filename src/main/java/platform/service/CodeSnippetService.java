package platform.service;

import java.util.List;

import platform.dto.CodeSnippetDTO;
import platform.dto.CodeSnippetIdDTO;

public interface CodeSnippetService {

    CodeSnippetIdDTO postCodeSnippet(CodeSnippetDTO codeSnippetDTO);

    List<CodeSnippetDTO> getLatest10CodeSnippets();

    CodeSnippetDTO getCodeSnippetById(CodeSnippetIdDTO codeSnippetIdDTO);

}
