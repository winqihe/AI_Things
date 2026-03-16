#!/usr/bin/env node

import { Agent } from './agent.js';
import { skillRegistry } from './skills/registry.js';

// Initialize the agent
const agent = new Agent({
  skills: skillRegistry
});

// Simple CLI interface
async function main() {
  console.log('🤖 Skills Agent v0.1.0');
  console.log('Available skills:', agent.listSkills().join(', '));
  console.log('\nType your request (or "exit" to quit):\n');

  process.stdin.setEncoding('utf8');
  process.stdin.resume();

  for await (const line of process.stdin) {
    const input = line.trim();
    if (!input) continue;

    if (input === 'exit' || input === 'quit') {
      console.log('Goodbye! 👋');
      process.exit(0);
    }

    if (input === 'help') {
      console.log('\nAvailable skills:');
      for (const [name, skill] of skillRegistry) {
        console.log(`  ${name}: ${skill.description}`);
      }
      console.log();
      continue;
    }

    try {
      const response = await agent.process(input);
      console.log(`\n🤖 ${response}\n`);
    } catch (error) {
      console.error(`\n❌ Error: ${error.message}\n`);
    }
  }
}

main().catch(console.error);
