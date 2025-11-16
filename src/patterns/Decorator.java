package patterns;

interface Notifier { void send(String msg); }

class EmailNotifier implements Notifier {
    @Override public void send(String msg) { System.out.println("[Decorator] Email: " + msg); }
}

abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrappee;
    protected NotifierDecorator(Notifier wrappee) { this.wrappee = wrappee; }
    @Override public void send(String msg) { wrappee.send(msg); }
}

class SMSDecorator extends NotifierDecorator {
    public SMSDecorator(Notifier wrappee) { super(wrappee); }
    @Override public void send(String msg) {
        super.send(msg);
        System.out.println("[Decorator] SMS: " + msg);
    }
}

class SlackDecorator extends NotifierDecorator {
    public SlackDecorator(Notifier wrappee) { super(wrappee); }
    @Override public void send(String msg) {
        super.send(msg);
        System.out.println("[Decorator] Slack: " + msg);
    }
}