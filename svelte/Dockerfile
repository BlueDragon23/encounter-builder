FROM node:18-alpine

WORKDIR ./application
# Copy package json/package lock
COPY package.json .
COPY package-lock.json .
# Install dependencies
RUN npm ci --omit dev
# Copy built code
COPY build ./build
# Set entrypoint
ENTRYPOINT ["node", "build"]