<#import "parts/common.ftlh" as c>
<#import "parts/codeSnippet.ftlh" as s>

<@c.page "Latest">
    <ul class="list">
        <#list snippets as snippet>
            <li>
                <@s.codeSnippet snippet/>
            </li>
        <#else>
            No code snippets!
        </#list>
    </ul>
</@c.page>
