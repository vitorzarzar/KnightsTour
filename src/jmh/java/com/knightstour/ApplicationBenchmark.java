package com.knightstour;

import com.knightstour.service.ChessPathService;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ApplicationBenchmark {

    @Benchmark
    public void findPathFromRandomTile() {
        ChessPathService.findPath(ApplicationUnitTest.generateTile());
    }
}
