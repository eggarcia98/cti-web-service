@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(
        name = "findByName",
        query = "from Product p where p.name = :name"
    )
})
package com.ctiwebservice.model;