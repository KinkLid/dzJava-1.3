package patterns;

interface Image { void display(); }

class RealImage implements Image {
    private final String path;
    RealImage(String path) { this.path = path; loadFromDisk(); }
    private void loadFromDisk() { System.out.println("[Proxy] Loading from disk: " + path); }
    @Override public void display() { System.out.println("[Proxy] Displaying: " + path); }
}

class ImageProxy implements Image {
    private final String path;
    private RealImage real;
    ImageProxy(String path) { this.path = path; }
    @Override public void display() {
        if (real == null) real = new RealImage(path);
        real.display();
    }
}