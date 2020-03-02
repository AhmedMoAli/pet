const app = document.getElementById('root')

const logo = document.createElement('img')
logo.src = 'logo.png'

const container = document.createElement('div')
container.setAttribute('class', 'container')

app.appendChild(logo)
app.appendChild(container)

var request = new XMLHttpRequest()
request.open('GET', '../api/v1/opportunities', true)
request.onload = function() {
  // Begin accessing JSON data here
  var data = JSON.parse(this.response)
  console.log(request)
  if (request.status == 200) {
    data.forEach(opportunity => {
      const card = document.createElement('div')
      card.setAttribute('class', 'card')
      
      const p = document.createElement('p')
      p.textContent = 'The representative {'+opportunity.representative.name+'}, {'+opportunity.representative.email+
      '}, {'+opportunity.representative.phone+'} has to visit {'+opportunity.company.name+'} at {'+opportunity.company.address+'} and talk to {'+opportunity.company.contact.name+'}, {'+ opportunity.company.contact.email + '}, {' + opportunity.company.contact.phone+'}'
      
     
      container.appendChild(card)
      card.appendChild(p)
    })
  } else {
	  
    const errorMessage = document.createElement('div')
    var jsonResponse= JSON.parse(this.responseText);
    errorMessage.innerHTML = '<p>Something went wrong!<p></br> Error {'+ jsonResponse.error + '} for the requested resource {'+jsonResponse.path+'}'
    
    app.appendChild(errorMessage)
  }
}

request.send()