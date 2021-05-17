<#import "../parts/common.ftlh" as c>

<@c.page "Error">
    <h1>CodeSnippet Platform ERROR 400</h1>
    <table>
        <tr>
            <td>Date</td>
            <td>${timestamp?datetime}</td>
        </tr>
        <tr>
            <td>Error</td>
            <td>${error}</td>
        </tr>
        <tr>
            <td>Status</td>
            <td>${status}</td>
        </tr>
        <tr>
            <td>Message</td>
            <td>${message}</td>
        </tr>
    </table>
</@c.page>
