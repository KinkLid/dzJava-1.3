package patterns;

interface CompressionStrategy { byte[] compress(byte[] data); }

class ZipStrategy implements CompressionStrategy {
    @Override public byte[] compress(byte[] data) {
        System.out.println("[Strategy] ZIP compress");
        return data;
    }
}
class RarStrategy implements CompressionStrategy {
    @Override public byte[] compress(byte[] data) {
        System.out.println("[Strategy] RAR compress");
        return data;
    }
}
class Compressor {
    private CompressionStrategy strategy;
    public Compressor(CompressionStrategy strategy) { this.strategy = strategy; }
    public void setStrategy(CompressionStrategy strategy) { this.strategy = strategy; }
    public byte[] compress(byte[] data) { return strategy.compress(data); }
}