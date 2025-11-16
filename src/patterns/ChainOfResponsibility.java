package patterns;

class Request {
    boolean authenticated;
    boolean valid;
    String payload;
    Request(boolean authenticated, boolean valid, String payload) {
        this.authenticated = authenticated; this.valid = valid; this.payload = payload;
    }
}

abstract class Handler {
    private Handler next;
    public Handler setNext(Handler next) { this.next = next; return next; }
    public void handle(Request r) { if (doHandle(r) && next != null) next.handle(r); }
    protected abstract boolean doHandle(Request r);
}

class AuthHandler extends Handler {
    @Override protected boolean doHandle(Request r) {
        if (!r.authenticated) { System.out.println("[Chain] Auth failed"); return false; }
        System.out.println("[Chain] Auth OK"); return true;
    }
}
class ValidationHandler extends Handler {
    @Override protected boolean doHandle(Request r) {
        if (!r.valid) { System.out.println("[Chain] Validation failed"); return false; }
        System.out.println("[Chain] Validation OK"); return true;
    }
}
class BusinessHandler extends Handler {
    @Override protected boolean doHandle(Request r) {
        System.out.println("[Chain] Business processed: " + r.payload);
        return true;
    }
}