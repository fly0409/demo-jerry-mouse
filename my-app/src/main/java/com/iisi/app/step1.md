## 實現簡單的HTTP server

### 透過lib com.sun.net.httpserver來實作


    HttpServer: 通過IP address和port,定義一個HTTP服務物件

    HttpHandler: 處理http請求的核心方法,必須實作handle(HttpExchange)

    HttpExchange: 讀取http請求的輸入,並輸出http回應

在SimpleHttpHandler裡面
    實作了一個,接收到請求後,回傳200並帶有body

    <h1>Hello, world.</h1><p>
    
這樣固定的回應

在SimpleHttpServer啟動server
並在對應的context註冊這個handler