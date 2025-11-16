package patterns;

interface PaymentProcessor { void pay(String currency, long cents); }

class OldPaymentAPI {
    void sendPayment(String isoCurrency, int amountInCents) {
        System.out.println(String.format("[Adapter] Old API paid %d %s", amountInCents, isoCurrency));
    }
}

class OldPaymentAdapter implements PaymentProcessor {
    private final OldPaymentAPI api;
    OldPaymentAdapter(OldPaymentAPI api) { this.api = api; }

    @Override public void pay(String currency, long cents) {
        if (cents > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Old API limit exceeded");
        api.sendPayment(currency, (int) cents);
    }
}