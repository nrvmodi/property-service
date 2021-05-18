package com.speedhome.web.rest;

import com.google.common.collect.Lists;
import com.speedhome.api.PropertyApiService;
import com.speedhome.config.ApplicationProperties;
import com.speedhome.dto.PropertyDTO;
import com.speedhome.vm.NewPropertyVM;
import com.speedhome.vm.UpdatePropertyVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class PropertyResource {

    @Autowired
    private PropertyApiService propertyApiService;

    @Autowired
    private ApplicationProperties applicationProperties;

    /**
     * properties
     * <p>
     * //     * @param matrixVars
     *
     * @param pageable
     * @return
     */
    @GetMapping("/properties{matrixVars}")
    public ResponseEntity<List<PropertyDTO>> getAll(/*@MatrixVariable Map<String, List<Object>> matrixVars*/
            @RequestParam Map<String, String> requestParams,
            Pageable pageable) {
        log.debug("REST request to get properties");

        Page<PropertyDTO> page = null;

        if (MapUtils.isNotEmpty(requestParams)) {
            Map<String, List<Object>> matrixVars = new HashMap<>();
            requestParams.keySet().forEach(key -> matrixVars.put(key, Lists.newArrayList(requestParams.get(key))));
            page = propertyApiService.getAll(matrixVars, pageable);
        } else {
            page = propertyApiService.getAll(pageable);
        }

        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    /**
     * POST  /properties :Creates new property.
     *
     * @param propertyVM the property details
     */
    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> create(@RequestBody NewPropertyVM propertyVM) throws URISyntaxException {
        log.debug("REST request to save Property : {}", propertyVM);

        PropertyDTO property = propertyApiService.addProperty(propertyVM);

        return ResponseEntity.created(new URI("/api/properties/" + property.getId()))
                .body(property);
    }

    /**
     * GET  /properties/{id} : Get property by Id.
     *
     * @param id the id of property
     * @return the property
     */
    @GetMapping("/properties/{id}")
    public ResponseEntity<PropertyDTO> get(@PathVariable("id") UUID id) {
        log.debug("REST request to get property {}", id);

        return ResponseEntity.ok().body(propertyApiService.getProperty(id));
    }

    /**
     * PUT  /properties/{id} : Update property by Id.
     *
     * @param propertyDTO the json of property
     * @return the property
     */
    @PutMapping("/properties/{id}")
    public ResponseEntity<PropertyDTO> update(@PathVariable("id") UUID id, @RequestBody UpdatePropertyVM propertyDTO) {
        log.debug("REST request to update property {}", propertyDTO);

        PropertyDTO updated = propertyApiService.update(id, propertyDTO);

        return ResponseEntity.ok().body(updated);
    }

    /**
     * GET  /properties/{id} : Get property by Id.
     *
     * @param id the id of property
     * @return the property
     */
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<PropertyDTO> delete(@PathVariable("id") UUID id) {
        log.debug("REST request to delete property {}", id);

        PropertyDTO property = propertyApiService.getProperty(id);
        propertyApiService.delete(id);
        return ResponseEntity.ok().body(property);
    }

}
