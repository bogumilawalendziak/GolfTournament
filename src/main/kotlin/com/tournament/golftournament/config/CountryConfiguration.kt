package com.tournament.golftournament.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "country")
class CountryOverrideProperties {
    var countries: Map<String, String> = emptyMap()
}
