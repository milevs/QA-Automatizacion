import Ajv from "ajv";
 
Cypress.Commands.add('validateSchema', (responseBody, schema) => {
    const ajv = new Ajv({ allErrors: true });
    const valid = ajv.validate(schema, responseBody);
    expect(valid, JSON.stringify(ajv.errors)).to.equal(true);
});
 
Cypress.Commands.add('requestSend', (method, url, body, expectedStatusCode, schema) =>{
    cy.request({ method: method, url: url, body:body}).then( (response) =>{
        cy.validateSchema(response.body, schema)
        expect(response.status).to.equal(expectedStatusCode)
    })
})
 