package com.ideologyCreativeStudio.test.businesslayer.interfaces.generics;

public interface Mapper <D, S>{

    S map(D input);
}
