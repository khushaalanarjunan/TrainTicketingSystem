package com.example.trainticketsystem_hashmapbeatstherest.enums;

public enum Station {
    ARAU ("Arau"),
    PADANG_BESAR("Padang Besar"),
    ALOR_SETAR("Alor Setar"),
    SUNGAI_PETANI("Sungai Petani"),
    PENANG("Penang"),
    BUKIT_MERTAJAM("Bukit Mertajam"),
    SINGAPORE("Singapore"),
    IPOH("Ipoh"),
    KAMPAR("Kampar"),
    BUTTERWORTH("Butterworth"),
    BATU_GAJAH("Batu Gajah"),
    KAJANG("Kajang"),
    RAWANG("Rawang"),
    GEMAS("Gemas"),
    KUALA_LUMPUER("Kuala Lumpur");

    public final String name;
    Station(String name) {
        this.name = name;
    }
}
