/* create index */
import React from 'react';
import { GetServerSideProps } from 'next';

import { fetchClients } from './services/fetchData';
import { Client } from './services/fetchData';

import { ModalInvoices } from './components/ModalInvoices';

import styled from 'styled-components';
import { Clients } from './components/Clients';

const Container = styled.div`
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	justify-content: space-between;
	align-items: center;
	align-content: center;
	height: 100%;
	width: 100%;
`;

const List = styled.div`
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	justify-content: space-between;
	align-items: center;
	align-content: center;
	height: 100%;
	width: 100%;
`;

interface Props {
	clients: Client[];
}

export default function Home({ clients }: Props) {
	const [isOpen, setIsOpen] = React.useState(false);
	const [clientId, setClientId] = React.useState<Number | undefined>();

	return (
		<Container>
			<List>
				{clients.map((client) => (
					<Clients
						key={client.id}
						title={client.name}
						email={client.email}
						onClick={() => {
							setClientId(client.id);
							setIsOpen(true);
						}}
					></Clients>
				))}
			</List>
			<ModalInvoices
				isOpen={isOpen}
				onClose={() => setIsOpen(false)}
				clientId={clientId}
			>
				<h1>Modal</h1>
			</ModalInvoices>
		</Container>
	);
}

export const getServerSideProps: GetServerSideProps = async () => {
	const clients = await fetchClients();

	return {
		props: {
			clients,
		},
	};
};
