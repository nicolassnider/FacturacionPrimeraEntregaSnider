import Image from 'next/image';
import { Inter } from 'next/font/google';
import React, { useEffect, useState } from 'react';
import { Client, fetchData } from './services/fetchData';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
	const [clients, setClients] = useState<Client[]>([]);
	useEffect(() => {
		async function getData() {
			const result = await fetchData();
			setClients(result);
		}
		getData();
	}, []);

	return (
		<>
			{clients.map((client: Client) => (
				<div
					key={client.id}
					className="block mt-1 max-w-[22rem] rounded-lg bg-white p-6 shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700"
				>
					<h5 className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
						{client.name} {client.lastName}
					</h5>
					<p className="mb-4 text-base text-neutral-600 dark:text-neutral-200">
						{client.docNumber}
					</p>
				</div>
			))}
		</>
	);
}
