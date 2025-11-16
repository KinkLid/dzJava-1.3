package patterns;

public class Main {
    public static void main(String[] args) {
        // Strategy
        Compressor compressor = new Compressor(new ZipStrategy());
        compressor.compress("data".getBytes());
        compressor.setStrategy(new RarStrategy());
        compressor.compress("data".getBytes());

        // Chain of Responsibility
        Handler chain = new AuthHandler().setNext(new ValidationHandler()).setNext(new BusinessHandler());
        chain.handle(new Request(true, true, "process this payload"));
        chain.handle(new Request(false, true, "should stop at auth"));

        // Builder
        HttpRequest req = new HttpRequest.Builder()
                .method("POST")
                .url("https://api.example.com/items")
                .header("Authorization", "Bearer token")
                .body("{\"name\":\"item\"}")
                .build();
        System.out.println(req);

        // Proxy
        Image img = new ImageProxy("/images/pic.png");
        img.display(); // первая — загрузит и покажет
        img.display(); // вторая — только покажет, без повторной загрузки

        // Decorator
        Notifier notifier = new SlackDecorator(new SMSDecorator(new EmailNotifier()));
        notifier.send("Build finished successfully");

        // Adapter
        PaymentProcessor processor = new OldPaymentAdapter(new OldPaymentAPI());
        processor.pay("USD", 12_34); // $12.34
    }
}
