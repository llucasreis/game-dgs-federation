import { ApolloServer } from 'apollo-server';
import { ApolloGateway } from '@apollo/gateway';

const gateway = new ApolloGateway({
  serviceList: [
    {name: "games", url: "http://localhost:8080/graphql"},
    {name: "reviews", url: "http://localhost:8081/graphql"}
  ]
});

const server = new ApolloServer({ gateway });
server.listen().then(({ url }) => {
  console.log(`🚀 Gateway ready at ${url}`);
});