package org.home.hone.benchmark;

import org.home.hone.dip.Seal;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;

@State(Scope.Group)
public class SealBenchmark {

    private static volatile int seq;

    @Benchmark
    @Warmup(iterations = 5, time = 1)
    @Group("g1")
    @GroupThreads(10)
    @Measurement(iterations = 50, time = 1)
    @BenchmarkMode(Mode.Throughput)
    public int benchmarkSeal() {
        File seal = new File("/Users/justin/work/quicksand/pdfcut/seal.png");
        File src = new File("/Users/justin/work/quicksand/pdfcut/sa_sheet.jpg");
        String destFile = String.format("sa_sheet-java-sealed-%05d.jpg", ++seq);
        File dest = new File(destFile);
        return Seal.seal(seal, src, dest, 200, 240, 1.0f);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(SealBenchmark.class.getSimpleName())
            .forks(1)
            .jvmArgsAppend("-Djava.awt.headless=true")
            .build();

        new Runner(opt).run();
    }
}
