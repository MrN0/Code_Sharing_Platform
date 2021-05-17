<#import "parts/common.ftlh" as c>

<@c.page "Create">
    <div class="snippet snippet_form">
        <label for="code_snippet"></label>
        <textarea class="code_area" id="code_snippet" placeholder="// write your code here"></textarea>

        <label for="time">Time restriction:</label>
        <input type="number" value="0" min="0" id="time" name="time">

        <label for="views">Views restriction:</label>
        <input type="number" value="0" min="0" id="views" name="views">

        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </div>
    <script src="/js/app.js"></script>
</@c.page>
