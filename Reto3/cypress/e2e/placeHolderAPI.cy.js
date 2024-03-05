 
const schemaGetToDo = {
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "array",
  "items": {
    "type": "object",
    "properties": {
      "userId": {
        "type": "integer"
      },
      "id": {
        "type": "integer"
      },
      "title": {
        "type": "string"
      },
      "completed": {
        "type": "boolean"
      }
    },
    "required": ["userId", "id", "title", "completed"],
    "additionalProperties": false
  }
};


const schemaGetPosts = {
"$schema": "http://json-schema.org/draft-07/schema#",
  "type": "array",
  "items": {
    "type": "object",
    "properties": {
      "userId": {
        "type": "integer"
      },
      "id": {
        "type": "integer"
      },
      "title": {
        "type": "string"
      },
      "body": {
        "type": "string"
      }
    },
    "required": ["userId", "id", "title", "body"],
    "additionalProperties": false
  }
};

const schemaGetAlbums = {

    "$schema": "http://json-schema.org/draft-07/schema#",
    "type": "array",
    "items": {
      "type": "object",
      "properties": {
        "userId": {
          "type": "integer"
        },
        "id": {
          "type": "integer"
        },
        "title": {
          "type": "string"
        }
      },
      "required": ["userId", "id", "title"]
    }
  };
  
    


describe('Pruebas para Rest Api en Place holder', () => {
  it('obtener lista de To Do ', () => {
   
    cy.log ("-------------obtener lista de To Do----------------")
    const url = 'https://jsonplaceholder.typicode.com/todos'
    const methodRest = 'GET'

    cy.log (url)
    cy.log (methodRest)

    cy.requestSend(methodRest, url, null, 200, schemaGetToDo)
  })

  it('obtener lista de Post ', () => {
   
    cy.log ("-------------obtener lista de Post----------------")
    const url = 'https://jsonplaceholder.typicode.com/posts'
    const methodRest = 'GET'

    cy.log (url)
    cy.log (methodRest)

    cy.requestSend(methodRest, url, null, 200, schemaGetPosts)
  })

  it('obtener lista de Albums ', () => {
   
    cy.log ("-------------obtener lista de Albums----------------")
    const url = 'https://jsonplaceholder.typicode.com/albums'
    const methodRest = 'GET'

    cy.log (url)
    cy.log (methodRest)

    cy.requestSend(methodRest, url, null, 200, schemaGetAlbums)
  })
})
