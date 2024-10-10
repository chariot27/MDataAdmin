package com.chariot.nm.providers.api.response;

public record CometResponse(
        String object,
        String epoch_tdb,
        String tb_tdb,
        String e,
        String i_deg,
        String w_deg,
        String node_deg,
        String q_au_1,
        String q_au_2,
        String p_yr,
        String moid_au,
        String ref,
        String object_name
){
}
