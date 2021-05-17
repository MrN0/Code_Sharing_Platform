# Code Sharing Platform
Sometimes, it's useful to have a tool that can help you share a piece of code with other programmers. Actually, there is a [website called Pastebin](https://pastebin.com/) that does exactly that. A huge downside of Pastebin is that every piece of code you share automatically becomes available for the public. This could present a problem since many programmers work under the NDA (Non-disclosure agreement) which prohibits the use of such services to prevent the source code from leaking.

If there is a team of programmers who work in the same company and want to exchange pieces of code, they can solve this problem by using their own implementation of Pastebin. Such a web service is supposed to be accessible only locally, not via the Internet. This project is my  own secure version of a code-sharing platform.

## Table of Contents
* [About this program](#about-this-program)
* [Technologies](#technologies)
* [Program Description](#program-description)
* [Example](#example)

## About this program
This project is a solution to the problem of JetBrains Academy - "Code Sharing Platform".

The program has two types of interfaces: API and WEB interface. The API interface is accessed through endpoints that start with `/api` while web interface endpoints starts with `/`. The API interface returs data as `JSON`, while the web interface uses `HTML`, `JS`, and `CSS`.

There are two limitations on the code snippet's visibility:
1.  **A limit on the number of views**  will allow viewing the snippet only a certain number of times, after which the snippet is deleted from the database.
2.  **A limit on the viewing time** will allow viewing a code snippet for a certain period of time, and after its expiration, the code snippet is deleted from the database.

If both restrictions are applied to a certain code snippet, it has to be deleted when at least one of these limits is reached.

## Technologies
- Gradle 7.0.2
- JDK 11
- Spring Boot 2.4.4
- Freemarker 2.3.31
- H2 Database Engine

## Program Description
Code snippets are accessible via UUID links.

### API
`GET /api/code/UUID`  returns JSON with the UUIDs uploaded code snippet. It contains four fields:
- `code` field contains code snipped.
- `date` field contains the creation date.
- `time` field contains the time (in seconds) during which the snippet is accessible.
- `views` field shows how many additional views are allowed for this snippet (excluding the current one).

`GET /api/code/latest`  returns a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest. **It does not return any restricted snippets**.

`POST /api/code/new`  takes a JSON object with three fields `code`, `time` and `views` and returns a JSON with a single field `id` (UUID of the snippet).
- `code` field contains the code snippet.
- `time` field contains the time (in seconds) during which the snippet is accessible.
- `views` field contains a number of views allowed for this snippet.
- 0 and negative values correspond to the **absence** of the restriction.
- POST request should contain numbers, not strings.

### WEB
`GET /code/UUID`  returns HTML that contains:
- the UUIDs uploaded code snippet.
- the creation date.
- the time (in seconds) during which the snippet is accessible -in case the time restriction is applied.
- allowed views (excluding the current one) - in case the views restriction is applied.

`GET /code/latest`  returns HTML that contains 10 most recently uploaded code snippets. **It does not return any restricted snippets**.

`GET /code/new`  returns HTML that contains such fields:
- textfield for code snippet
- field for time restriction
- field for views restriction

## Example

**Example 1**

Request  `POST /api/code/new`  with the following body:

```json
{
	"code": "class Code { ...",
	"time": 0,
	"views": 0
}
```

Response:  `{ "id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f" }`.

Request  `POST /api/code/new`  with the following body:

```json
{
	"code": "public static void ...",
	"time": 0,
	"views": 0
}
```

Response:  `{ "id" : "e6780274-c41c-4ab4-bde6-b32c18b4c489" }`.

Request  `POST /api/code/new`  with the following body:

```json
{
	"code": "Secret code",
	"time": 5000,
	"views": 5
}
```

Response:  `{ "id" : "2187c46e-03ba-4b3a-828b-963466ea348c" }`.

**Example 2**

Request:  `GET /api/code/2187c46e-03ba-4b3a-828b-963466ea348c`

Response:

```json
{
	"code": "Secret code",
	"date": "2020/05/05 12:01:45",
	"time": 4995,
	"views": 4
}
```

Another request  `GET /api/code/2187c46e-03ba-4b3a-828b-963466ea348c`

Response:

```json
{
	"code": "Secret code",
	"date": "2020/05/05 12:01:45",
	"time": 4991,
	"views": 3
}
```

**Example 3**

Request:  `GET /code/2187c46e-03ba-4b3a-828b-963466ea348c`

Response:

![](https://ucarecdn.com/236ce1cf-524c-49be-909c-eeccee0ffa53/)

**Example 4**

Request:  `GET /api/code/latest`

Response:

```json
[
	{
		"code": "public static void ...",
		"date": "2020/05/05 12:00:43",
		"time": 0,
		"views": 0
	},
	{
		"code": "class Code { ...",
		"date": "2020/05/05 11:59:12",
		"time": 0,
		"views": 0
	}
]
```

**Example 5**

Request:  `GET /code/latest`

Response:

![](https://ucarecdn.com/8b62a89d-3cb8-4093-a7e8-8e63cadbf1fd/)

**Example 6**

Request:  `GET /code/new`

Response:

![](https://ucarecdn.com/a6ff06f0-ed0a-43d9-8893-2b0cd68b694b/)
