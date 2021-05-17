package platform.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import platform.entity.CodeSnippet;

@Repository
public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, UUID> {

    List<CodeSnippet> findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(long time, long views);

}
