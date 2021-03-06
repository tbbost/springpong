package org.pongmatcher.web;

import org.pongmatcher.domain.Match;
import org.pongmatcher.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "matches", description = "Endpoint for Match specific operations")
final class MatchesController {

    private final MatchRepository matchRepository;

    @Autowired
    MatchesController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/matches/{id}")
    @ApiOperation(value = "Get Match", notes = "Returns match items")
    ResponseEntity<Match> show(@PathVariable String id) {
        Match match = matchRepository.findOne(id);

        if (match == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(match, HttpStatus.OK);
        }
    }

}
