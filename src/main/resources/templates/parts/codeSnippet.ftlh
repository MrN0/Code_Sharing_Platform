<#macro codeSnippet snippet>
    <div class="snippet">
        <div><span id="load_date">${snippet.date}</span></div>
        <#if snippet.viewsLimited>
            <div><span id="views_restriction">${snippet.views}</span> more views allowed</div>
        </#if>
        <#if snippet.timeLimited>
            <div>The code will be available for <span id="time_restriction">${snippet.time}</span> seconds</div>
        </#if>
        <div><pre id="code_snippet"><code>${snippet.code}</code></pre></div>
    </div>
</#macro>
